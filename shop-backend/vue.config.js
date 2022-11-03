module.exports = {
    devServer: {
        proxy: {
            // cloud-backend-item:9011
            '/product_api': {
                // target: 'http://127.0.0.1:9011', //全部通过网关服务7070去代理
                target: 'http://vUbuntu5:7071',
                pathRewrite: {
                    '^/product_api': ''
                },
                changeOrigin: true
            },
            // cloud-backend-content:9021
            '/content_api': {
                // target: 'http://127.0.0.1:9021',
                target: 'http://vUbuntu5:7071',
                pathRewrite: {
                    '^/content_api': ''
                },
                changeOrigin: true
            },
            "/api":{
                target: 'http://localhost:3000',
                pathRewrite: {
                    '^/api': ''
                },
                changeOrigin: true
            }
        }
    }
}