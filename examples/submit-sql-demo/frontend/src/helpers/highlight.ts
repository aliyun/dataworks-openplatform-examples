import colors from 'colors/safe';

export type Theme = string | string[];
export interface Rule {
  regex: RegExp;
  theme: Theme;
}
export interface Brush {
  rules: Rule[];
}

colors.enable();

export function convert(char: string, theme: Theme) {
  let nextThemes: string[] = typeof theme === 'string' ? [theme] : theme;
  const fn: any = nextThemes.reduce((c, theme) => (c as any)[theme], colors);
  return fn(char);
}
export function text(txt: string, brush?: Brush) {
  let nextText = txt;
  brush && brush.rules.forEach((rule) => {
    nextText = nextText.replace(rule.regex, char => convert(char, rule.theme));
  });
  return nextText;
}
