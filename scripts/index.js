const glob = require('glob');
const fs = require('fs');

function getRootConfig() {
  const content = fs.readFileSync('./application.properties', 'utf-8');
  const lines = content.split('\n');
  const rootConfig = {};
  lines.forEach((line) => {
    if (line && !line.includes?.('##')) {
      const [key, value] = line.split('=');
      rootConfig[key] = value;
    }
  });
  return rootConfig;
}
function inject(rootConfig) {
  glob("examples/**/application.properties", null, function (er, files) {
    files.forEach((file) => {
      const content = fs.readFileSync(file, 'utf-8');
      const lines = content.split('\n');
      const nextLines = lines.map((line) => {
        if (line && !line.includes?.('##')) {
          const [key, value] = line.split('=');
          const nextValue = rootConfig[key] || value;
          return `${key}=${nextValue}`;
        }
        return line;
      });
      const nextContent = nextLines.join('\n');
      fs.writeFileSync(file, nextContent);
    });
  });
}
function main() {
  const rootConfig = getRootConfig();
  inject(rootConfig);
};

main();
