module.exports = {
    devServer: {
        proxy: {
            '/product_api': {
                target: 'http://127.0.0.1:9011',    
                pathRewrite: {
                    '^/product_api': ''
                },
                changeOrigin: true
            },
            '/content_api': {
                target: 'http://127.0.0.1:9021',    
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