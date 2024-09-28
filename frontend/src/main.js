import { createApp } from 'vue'
import { createPinia } from 'pinia'
import Modal from './components/Modal.vue'; // Modal 컴포넌트 경로

import App from './App.vue'
import router from './router'
import BootstrapVue3 from 'bootstrap-vue-3'

import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-vue-3/dist/bootstrap-vue-3.css';

const app = createApp(App)

app.component('Modal', Modal);
app.use(createPinia())
app.use(router)
app.use(BootstrapVue3)

app.mount('#app')
