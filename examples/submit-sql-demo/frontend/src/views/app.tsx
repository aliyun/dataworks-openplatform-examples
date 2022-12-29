import { useEffect, useRef, useState, useCallback } from 'react';
import type { FunctionComponent } from 'react';
import cn from 'classnames';
import * as monaco from 'monaco-editor';
import { Terminal } from 'xterm';
import { FitAddon } from 'xterm-addon-fit';
import { Tree, Select, Message } from '@alifd/next';
import * as highlight from '../helpers/highlight';
import * as services from '../services';
import classes from '../styles/app.module.css';

export interface Props {}
export interface NextTerminal extends Terminal {
  inputText?: string;
  stack: string[];
  pointer: number;
}

const brush = {
  rules: [
    { regex: /\bERROR\b/gmi, theme: 'red' },
    { regex: /\bWARN\b/gmi, theme: 'yellow' },
    { regex: /\bINFO\b/gmi, theme: 'green' },
    { regex: /^FAILED:.*$/gmi, theme: 'red' },
  ],
};
// 示例业务流程名称
const openPlatformBusinessName = '开放平台示例业务流程';
// 编辑器创建参数
const editorOptions = {
  content: '',
  language: 'sql',
  theme: 'vs-dark',
  automaticLayout: true,
  fontSize: 16,
};
// Terminal创建参数
const terminalOptions = {
  cursorBlink: true,
  cursorStyle: 'underline' as const,
  fontSize: 16,
};
const validCommands = [
  'run',
];

/**
 * 展示错误信息弹窗的方法
 * @param message 错误信息
 */
function showError(message: string) {
  Message.error({ title: 'Error Message', content: message });
}
/**
 * 展示提示信息弹窗的方法
 * @param message 提示信息
 */
function showTips(message: string) {
  Message.show({ title: 'Tips', content: message });
}
/**
 * 处理提交任务的方法，当terminal中输入dw run ...时触发
 * @param term terminal实例
 * @param dataSource 目录树数据源
 * @param workspace 工作空间id
 */
async function commandHandler(term: NextTerminal, dataSource: any, workspace?: number) {
  term.write('\r\n$ ');
  const input = term.inputText;
  term.inputText = '';
  if (['', undefined].includes(input)) {
    return;
  }
  term.stack = [input!, ...term.stack];
  term.pointer = -1;
  if (!workspace) {
    term.write(highlight.text('[ERROR] You should select workspace first.\r\n$ ', brush));
    return;
  }
  // 这里简单的处理了下输入的命令行解析，如果命令开头为dw，且执行命令为run就继续往下处理，否则报错
  const words = input?.split(' ');
  const tag = words?.[0].toLowerCase();
  const command = words?.[1]?.toLowerCase();
  const fileName = words?.[2];
  if (tag !== 'dw' || !validCommands.includes(command!)) {
    term.write(highlight.text('[ERROR] Invalid command.\r\n$ ', brush));
    return;
  }
  // 获取输入文件
  const source = dataSource?.[0]?.children.find((i: any) => i.label === fileName);
  const file = await services.ide.getFile(workspace, source.key);
  if (!file) {
    term.write(highlight.text('[ERROR] File name does not exist.\r\n$ ', brush));
    return;
  }
  term.write(highlight.text('[INFO] Submiting file.\r\n$ ', brush));
  // 调用部属文件接口，将文件发布到调度系统中
  const response = await services.ide.deployFile(workspace, source.key);
  if (response) {
    term.write(highlight.text('[INFO] Submit file success.\r\n$ ', brush));
  } else {
    term.write(highlight.text('[ERROR] Submit file failed.\r\n$ ', brush));
    return;
  }
  // 执行冒烟测试，运行调度任务
  let dag: services.ide.Dag;
  try {
    term.write(highlight.text('[INFO] Start to run task.\r\n$ ', brush));
    dag = (await services.ide.runSmoke(workspace, file.nodeId, openPlatformBusinessName))[0];
    term.write(highlight.text('[INFO] Trigger sql task success.\r\n$ ', brush));
  } catch (e) {
    term.write(highlight.text('[ERROR] Trigger sql task failed.\r\n$ ', brush));
    return;
  }
  // 轮询获取任务日志
  const event = setInterval(async () => {
    try {
      const logInfo = await services.ide.getLog(dag.instanceId, 'DEV');
      let log: string;
      switch (logInfo.instance.status) {
        case 'WAIT_TIME':
          log = '等待定时时间到来';
          break;
        case 'WAIT_RESOURCE':
          log = '等待资源...';
          break;
        default:
          log = logInfo.instanceLog;
      }
      term.write(`${highlight.text(log, brush).replace(/\n/g, '\r\n')}\r\n$ `);
      const finished = ['SUCCESS', 'FAILURE', 'NOT_RUN'].includes(logInfo.instance.status);
      finished && clearInterval(event);
    } catch (e) {
      term.write(highlight.text('[ERROR] SQL Task run failed.\r\n$ ', brush));
      return;
    }
  }, 3000);
}
/**
 * 处理Terminal键盘事件
 * @param e 事件对象
 * @param term terminal实例
 * @param dataSource 目录树数据源
 * @param workspace 工作空间id
 */
function onTerminalKeyChange(e: { key: string; domEvent: KeyboardEvent; }, term: NextTerminal, dataSource: any, workspace?: number) {
  const ev = e.domEvent;
  const printable = !ev.altKey && !ev.ctrlKey && !ev.metaKey;
  term.inputText = typeof term.inputText === 'string' ? term.inputText : '';
  switch (ev.key) {
    case 'ArrowUp':
      term.pointer = term.pointer < (term.stack.length - 1) ? term.pointer + 1 : term.pointer;
      term.inputText = term.stack[term.pointer];
      term.write(`\x1b[2K\r$ ${term.inputText}`);
      break;
    case 'ArrowDown':
      term.pointer = term.pointer > -1 ? term.pointer - 1 : -1;
      term.inputText = term.pointer === -1 ? '' : term.stack[term.pointer];
      term.write(`\x1b[2K\r$ ${term.inputText}`);
      break;
    case 'ArrowLeft':
      (term as any)._core.buffer.x > 2 && printable && term.write(e.key);
      break;
    case 'ArrowRight':
      (term as any)._core.buffer.x <= (term.inputText.length + 1) && printable && term.write(e.key);
      break;
    case 'Enter':
      commandHandler(term, dataSource, workspace);
      break;
    case 'Backspace':
      if ((term as any)._core.buffer.x > 2) {
        term.inputText = term.inputText.slice(0, -1);
        term.write('\b \b');
      }
      break;
    default:
      if (printable) {
        term.inputText += e.key;
        term.write(e.key);
      }
  }
}
/**
 * 获取工作空间列表
 */
async function getWorkspaceList() {
  const response = await services.tenant.getProjectList();
  const list = response.projectList.filter(i => i.projectStatusCode === 'AVAILABLE').map(i => (
    { label: i.projectName, value: i.projectId }
  ));
  return list;
}
/**
 * 获取目录树数据源
 * @param workspace 工作空间id
 * @param dataSource 工作空间列表
 */
async function getTreeDataSource(workspace: number, dataSource: { label: string, value: number }[]) {
  try {
    const businesses = await services.ide.getBusinessList(workspace, openPlatformBusinessName);
    businesses.length === 0 && await services.ide.createBusiness(workspace, openPlatformBusinessName);
  } catch (e) {
    showError('You have no permission to access this workspace.');
    return;
  }
  const fileFolderPath = `业务流程/${openPlatformBusinessName}/MaxCompute`;
  const files = await services.ide.getFileList(workspace, fileFolderPath);
  let children: { key: number, label: string }[] = [];
  if (files.length === 0) {
    try {
      const currentWorkspace = dataSource.find(i => i.value === workspace);
      const file1 = await services.ide.createFile(workspace, currentWorkspace!.label, fileFolderPath, 'simpleSQL.mc.sql', 'SELECT 1');
      const file2 = await services.ide.createFile(workspace, currentWorkspace!.label, fileFolderPath, 'createTable.mc.sql', 'CREATE TABLE IF NOT EXISTS _qcc_mysql1_odps_first_20220113100903_done_ (\ncol string\n)\nCOMMENT \'全量数据同步完成标DONE表\'\nPARTITIONED BY\n(\nstatus STRING   COMMENT \'标DONE分区\'\n)\nLIFECYCLE 36500;');
      children = children.concat([
        { key: file1, label: 'simpleSQL.mc.sql' },
        { key: file2, label: 'createTable.mc.sql' },
      ]);
    } catch (e) {
      showError('Create file failed. The datasource odps_first does not exist.');
      return;
    }
  } else {
    children = files.map((i) => ({ key: i.fileId, label: i.fileName }));
  }
  return [{ key: 1, label: openPlatformBusinessName, children }];
}
/**
 * 获取文件详细信息
 * @param workspace 工作空间id
 * @param fileId 文件id
 */
async function getFileInfo(workspace: number, fileId: number) {
  const response = await services.ide.getFile(workspace, fileId);
  return response;
}
/**
 * 保存文件，当Ctrl+S时触发
 * @param workspace 工作空间id
 * @param editor 编辑器实例
 * @param selectedFile 已选择的文件
 */
async function saveFile(workspace: number, editor: monaco.editor.IStandaloneCodeEditor, selectedFile?: number) {
  if (!selectedFile) {
    showTips('Please select a file.');
    return;
  }
  const content = editor.getValue();
  const result = await services.ide.updateFile(workspace, selectedFile, { content });
  result ? showTips('Saved file') : showError('Failed to save file');
}

const App: FunctionComponent<Props> = () => {
  const editorRef = useRef<HTMLDivElement>(null);
  const termianlRef = useRef<HTMLDivElement>(null);
  const [terminal, setTerminal] = useState<NextTerminal>();
  const [editor, setEditor] = useState<monaco.editor.IStandaloneCodeEditor>();
  const [expnadedKeys, setExpandedKeys] = useState<any[]>();
  const [workspace, setWorkspace] = useState<number>();
  const [workspaces, setWorkspaces] = useState<{ label: string, value: number }[]>([]);
  const [dataSource, setDataSource] = useState<any[]>();
  const [selectedFile, setSelectedFile] = useState<number>();
  const [loading, setLoading] = useState<boolean>(false);
  // 创建编辑器实例
  useEffect(() => {
    if (editorRef.current) {
      const nextEditor = monaco.editor.create(editorRef.current, editorOptions);
      setEditor(nextEditor);
      return () => { nextEditor.dispose(); };
    }
  }, [editorRef.current]);
  // 添加保存文件按键事件
  useEffect(() => {
    editor?.addCommand(monaco.KeyMod.CtrlCmd | monaco.KeyCode.KeyS, () => {
      if (!workspace) {
        showTips('Please select workspace first');
        return;
      }
      saveFile(workspace, editor, selectedFile);
    });
  }, [editor, workspace, selectedFile]);
  // 创建terminal实例
  useEffect(() => {
    if (termianlRef.current) {
      const term: NextTerminal = new Terminal(terminalOptions) as any;
      term.pointer = -1;
      term.stack = [];
      setTerminal(term);
      const fitAddon = new FitAddon();
      term.loadAddon(fitAddon);
      term.open(termianlRef.current);
      fitAddon.fit();
      term.write('$ ');
      return () => { term.dispose(); };
    }
  }, [termianlRef.current]);
  // 注册terminal输入事件
  useEffect(() => {
    const event = terminal?.onKey(e => onTerminalKeyChange(e, terminal, dataSource, workspace));
    return () => {
      event?.dispose();
    };
  }, [terminal, dataSource, workspace]);
  // 获取目录树数据源
  useEffect(() => {
    workspace && (async () => {
      setLoading(true);
      const nextDataSource = await getTreeDataSource(workspace, workspaces);
      const defaultKey = nextDataSource?.[0]?.key;
      defaultKey && setExpandedKeys([defaultKey]);
      setDataSource(nextDataSource);
      setLoading(false);
    })();
  }, [workspace]);
  // 当目录树文件被点击时，获取文件详情并展示代码
  useEffect(() => {
    workspace && selectedFile && (async () => {
      setLoading(true);
      const file = await getFileInfo(workspace, selectedFile);
      editor?.setValue(file.content);
      editor?.getAction('editor.action.formatDocument').run();
      setLoading(false);
    })();
  }, [selectedFile]);
  // 获取工作空间列表
  useEffect(() => {
    (async () => {
      const list = await getWorkspaceList();
      setWorkspaces(list);
    })();
  }, []);
  const onExapnd = useCallback((keys: number[]) => { setExpandedKeys(keys); }, []);
  const onWorkspaceChange = useCallback((value: number) => { setWorkspace(value) }, []);
  const onTreeNodeSelect = useCallback((key: number[]) => { key[0] && setSelectedFile(key[0]) }, []);
  return (
    <div className={cn(classes.appWrapper)}>
      <div className={cn(classes.leftArea)}>
        <div className={cn(classes.workspaceWrapper)}>
          Workspace:
          <Select
            value={workspace}
            dataSource={workspaces}
            onChange={onWorkspaceChange}
            autoWidth={false}
            showSearch
          />
        </div>
        <div className={cn(classes.treeWrapper)}>
          <Tree
            dataSource={dataSource}
            isNodeBlock={{ defaultPaddingLeft: 20 }}
            expandedKeys={expnadedKeys}
            selectedKeys={[selectedFile]}
            onExpand={onExapnd}
            onSelect={onTreeNodeSelect}
            defaultExpandAll
          />
        </div>
      </div>
      <div className={cn(classes.rightArea)}>
        <div
          className={cn(classes.monacoEditorWrapper)}
          ref={editorRef}
        />
        <div
          className={cn(classes.panelWrapper)}
          ref={termianlRef}
        />
      </div>
      <div className={cn(classes.loaderLine)} style={{ display: loading ? 'block' : 'none' }} />
    </div>
  );
};

export default App;
