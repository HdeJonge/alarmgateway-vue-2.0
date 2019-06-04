import Vue from 'vue'
import App from './App.vue'
import VueResource from 'vue-resource'
import VueRouter from 'vue-router'
import {routes} from './routes';
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import Vuetify from 'vuetify'
Vue.use(Vuetify)
Vue.use(BootstrapVue)


Vue.config.productionTip = false

Vue.use(VueResource);

Vue.http.options.root = 'http://localhost:8080/api/';
//here you define fuctions you will execute on all requests
// Vue.http.interceptors.push((request, next) => {
//   console.log("intercepting request: " + request);
//   next(response => {
//     console.log("intercepting response: " + response);
//   });
// });

Vue.use(VueRouter);

const router = new VueRouter({
  routes: routes,//key:value
  mode:'history' // google vue-router (geen 3 in link)
})

new Vue({
  render: h => h(App),
  router,
}).$mount('#app')
