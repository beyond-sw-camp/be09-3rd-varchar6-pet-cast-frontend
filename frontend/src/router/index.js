import { createRouter, createWebHistory } from 'vue-router';
import Home from '../views/contents/Home.vue';
import About from '../views/contents/About.vue';
import RequestWrite from '@/components/request/RequestWrite.vue';

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home,
  },
  {
    path: '/about',
    name: 'About',
    component: About,
  },
  {
    path: '/request',
    name: 'request',
    component: RequestWrite,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;