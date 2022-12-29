import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';
import monacoEditorPlugin from 'vite-plugin-monaco-editor';

// https://vitejs.dev/config/
export default defineConfig({
  server: {
    open: true,
    port: 8080,
  },
  build: {
    chunkSizeWarningLimit: 2000,
  },
  define: {
    'process.env': process.env,
    'process.argv': process.argv,
  },
  css: {
    modules: {
      localsConvention: 'camelCaseOnly',
    },
  },
  plugins: [react(), (monacoEditorPlugin as any).default({})]
})
