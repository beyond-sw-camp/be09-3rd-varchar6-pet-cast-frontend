import { createRouter, createWebHistory } from 'vue-router';
import Home from '../views/contents/Home.vue';
import About from '../views/contents/About.vue';
import BizDetail from '@/views/contents/BizDetail.vue';
import BusinessEvents from '../views/contents/BusinessEvents.vue';
import BusinessReviews from '../views/contents/BusinessReviews.vue';
import BusinessQA from '../views/contents/BusinessQA.vue';

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
    path: '/api/v1/companies/:id',
    name: 'BizDetail',
    component: BizDetail
  },
  {
    path: '/business/:id/events',
    name: 'BusinessEvents',
    component: BusinessEvents
  },
  {
    path: '/business/:id/reviews',
    name: 'BusinessReviews',
    component: BusinessReviews
  },
  {
    path: '/business/:id/qa',
    name: 'BusinessQA',
    component: BusinessQA
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;