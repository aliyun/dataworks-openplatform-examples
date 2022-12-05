export default function(host: string, params: { [key: string]: any } = {}) {
  const search = new URLSearchParams(params);
  const url = new URL(host);
  url.search = search.toString();
  return url.toString();
}
