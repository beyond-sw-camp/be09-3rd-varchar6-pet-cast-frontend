import { createRouter, createWebHistory } from 'vue-router';
import Home from '../views/contents/Home.vue';
import About from '../views/contents/About.vue';
import BizDetail from '../views/contents/biz_detail/BizDetail.vue';
import BusinessEvents from '../views/contents/biz_event/BusinessEvents.vue';
import BusinessReviews from '../views/contents/biz_review/BusinessReviews.vue';
import BusinessEventUpdate from '../views/contents/biz_event/BusinessEventUpdate.vue';

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
    path: '/edit-event/:id',
    name: 'BusinessEventUpdate',
    component: BusinessEventUpdate
}
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;