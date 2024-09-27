import { createRouter, createWebHistory } from 'vue-router';
import Home from '../views/contents/Home.vue';
import About from '../views/contents/About.vue';
import RequestWrite from '@/components/request/RequestWrite.vue';
import RequestList from '@/components/request/RequestList.vue';

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  }, 
  {
    path: '/about',
    name: 'About',
    component: About
  },
  {
    path: '/request',
    name: 'request',
    component: RequestWrite
  },
  {
    path: '/requestlist',
    name: 'requestlist',
    component: RequestList
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;