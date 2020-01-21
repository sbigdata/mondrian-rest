import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './registerServiceWorker'
import vuetify from './plugins/vuetify';
import VueLayers from 'vuelayers'
import 'vuelayers/lib/style.css' // needs css-loader

Vue.use(VueLayers, {
    dataProjection: 'EPSG:5186',
})
Vue.config.productionTip = false

new Vue({
  router,
  store,
  vuetify,
  render: function (h) { return h(App) }
}).$mount('#app')
