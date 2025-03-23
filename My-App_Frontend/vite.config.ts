import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';
import svgr from 'vite-plugin-svgr';
import tsconfigPaths from 'vite-tsconfig-paths';

export default defineConfig({
  base: '/',
  appType: 'spa', // 👈 wichtig für BrowserRouter (SPA)

  plugins: [
    react(),
    tsconfigPaths(),
    svgr({
      include: ['src/**/*.svg'],
    }),
  ],

  server: {
    port: 5173,
    host: true,
  },

  build: {
    rollupOptions: {
      input: 'index.html',
    },
  },

  optimizeDeps: {
    force: true,
    esbuildOptions: {
      loader: {
        '.js': 'jsx',
      },
    },
  },
});

// import { defineConfig } from 'vite';
// import react from '@vitejs/plugin-react';

// export default defineConfig({
//   plugins: [react()],
//   appType: 'spa',
// });

