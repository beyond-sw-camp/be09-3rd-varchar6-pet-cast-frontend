import { createApp } from 'vue'
import { createPinia } from 'pinia'
import Modal from './components/Modal.vue'; // Modal 컴포넌트 경로

import App from './App.vue'
import router from './router'

const app = createApp(App)

app.component('Modal', Modal);
app.use(createPinia())
app.use(router)

app.mount('#app')
