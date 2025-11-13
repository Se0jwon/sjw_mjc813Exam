// vue.config.js
const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,

  // devServer 옵션 추가
  devServer: {
    proxy: {
      // '/api'로 시작하는 모든 요청은 target 주소로 전달됨
      '/api': {
        target: 'http://localhost:18888',
        changeOrigin: true,
      },
      '/images': {
        target: 'http://localhost:18888',
        changeOrigin: true,
      },
      '/hotel-images': {
        target: 'http://localhost:18888',
        changeOrigin: true,
      },
      '/user-images': {
        target: 'http://localhost:18888',
        changeOrigin: true,
      }
    }
  }
})