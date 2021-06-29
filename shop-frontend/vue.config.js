module.exports = {
    // publicPath: process.env.NODE_ENV === 'production'
    //     ? ''
    //     : '/',
    devServer: {
        proxy: {
            '/api': {
                target: 'http://127.0.0.1:9030',
                pathRewrite: {
                    '^/api': ''
                },
                changeOrigin: true
            },
            '/search_api': {
                target: 'http://127.0.0.1:9030',
                pathRewrite: {
                    '^/search_api': ''
                },
                changeOrigin: true
            },
            '/shopcar_api': {
                target: 'http://127.0.0.1:9030',
                pathRewrite: {
                    '^/shopcar_api': ''
                },
                changeOrigin: true
            },
            "/payment_api": {
                target: 'http://127.0.0.1:9030',
                pathRewrite: {
                    '^/payment_api': ''
                },
                changeOrigin: true
            },
            "/sxt": {
                target: 'http://127.0.0.1:9030',
                pathRewrite: {
                    '^/sxt': ''
                },
                changeOrigin: true
            },
            "/register_api": {
                target: 'http://127.0.0.1:9030',
                pathRewrite: {
                    '^/register_api': ''
                },
                changeOrigin: true
            }
        }
    }
}