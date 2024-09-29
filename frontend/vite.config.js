import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
      vue(),
    ],
    resolve: {
      alias: {
        '@': fileURLToPath(new URL('./src', import.meta.url))
      }
    },
  //   server: {
  //   proxy: {
  //     // 예시: '/api'로 시작하는 요청을 프록시
  //     '/api': {
  //       target: 'http://localhost:8081', // 실제 API 서버 주소
  //       changeOrigin: true,
  //       rewrite: (path) => path.replace(/^\/api/, '') // "/api" 경로를 제거하고 프록시 요청
  //     }
  //   }
  // }
})
