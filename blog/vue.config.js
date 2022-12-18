module.exports = {
  transpileDependencies: ["vuetify"],
  devServer: {
    proxy: {

      "/api": {
        target: "http://43.143.254.177:8080",
        changeOrigin: true,
        pathRewrite: {
          "^/api": ""
        }
      }
    },
    disableHostCheck: true
  },
  productionSourceMap: false,
  css: {
    extract: true,
    sourceMap: false
  }
};
