import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';
import svgr from 'vite-plugin-svgr';
import tsconfigPaths from 'vite-tsconfig-paths';

export default defineConfig({
  base: '/',                  // ⬅️ Basis-URL (bei Deployment ggf. anpassen)
  appType: 'spa',             // ⬅️ Für SPA Routing
  plugins: [
    react(),
    tsconfigPaths(),          // ⬅️ Pfade aus tsconfig.json
    svgr({
      include: ['src/**/*.svg'], // ⬅️ Nur SVGs aus src laden
    }),
  ],
  server: {
    port: 5173,
    host: true,
  },
  build: {
    rollupOptions: {
      input: './index.html',   // ⬅️ Absichernd mit relativer Pfadangabe
    },
    outDir: 'dist',            // ⬅️ Default; kann ggf. angepasst werden
    emptyOutDir: true,         // ⬅️ Dist-Ordner leeren vor Build
  },
  optimizeDeps: {
    esbuildOptions: {
      loader: {
        '.js': 'jsx',
      },
    },
  },
});
