module.exports = {
    // publicPath: process.env.NODE_ENV === 'production'
    //     ? ''
    //     : '/',
    devServer: {
        proxy: {
            '/api': {
                target: 'http://localhost:9011',
                pathRewrite: {
                    '^/api': ''
                },
                changeOrigin: true
            },
            '/search_api': {
                target: 'http://localhost:9011',
                pathRewrite: {
                    '^/search_api': ''
                },
                changeOrigin: true
            },
            '/shopcar_api': {
                target: 'http://localhost:9011',
                pathRewrite: {
                    '^/shopcar_api': ''
                },
                changeOrigin: true
            },
            "/payment_api": {
                target: 'http://localhost:9011',
                pathRewrite: {
                    '^/payment_api': ''
                },
                changeOrigin: true
            },
            "/sxt": {
                target: 'http://localhost:3001',
                pathRewrite: {
                    '^/sxt': ''
                },
                changeOrigin: true
            },
            "/register_api": {
                target: 'http://localhost:9011',
                pathRewrite: {
                    '^/register_api': ''
                },
                changeOrigin: true
            }
        }
    }
}