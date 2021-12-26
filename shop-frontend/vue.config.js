module.exports = {
    // publicPath: process.env.NODE_ENV === 'production'
    //     ? ''
    //     : '/',
    devServer: {
        proxy: {
            '/api': {
                // target: 'http://127.0.0.1:9030',  // 首页服务 
                target: 'http://127.0.0.1:7070',
                pathRewrite: {
                    '^/api': ''
                },
                changeOrigin: true
            },
            '/search_api': {
                // target: 'http://127.0.0.1:9100',    // 搜索服务
                target: 'http://127.0.0.1:7070',
                pathRewrite: {
                    '^/search_api': ''
                },
                changeOrigin: true
            },
            '/shopcar_api': {
                // target: 'http://127.0.0.1:9040',    // 购物车服务
                target: 'http://127.0.0.1:7070',
                pathRewrite: {
                    '^/shopcar_api': ''
                },
                changeOrigin: true
            },
            "/payment_api": {
                // target: 'http://127.0.0.1:9030',    // 订单服务
                target: 'http://127.0.0.1:7070',
                pathRewrite: {
                    '^/payment_api': ''
                },
                changeOrigin: true
            },
            "/sxt": {
                // target: 'http://127.0.0.1:3001',    // 
                target: 'http://127.0.0.1:7070',
                pathRewrite: {
                    '^/sxt': ''
                },
                changeOrigin: true
            },
            "/register_api": {
                // target: 'http://127.0.0.1:9090',    // 用户注册登录服务
                target: 'http://127.0.0.1:7070',
                pathRewrite: {
                    '^/register_api': ''
                },
                changeOrigin: true
            }
        }
    }
}