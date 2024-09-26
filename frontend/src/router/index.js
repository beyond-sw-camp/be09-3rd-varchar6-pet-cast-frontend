import { createRouter, createWebHistory } from 'vue-router';
import Home from '../views/contents/Home.vue';
import About from '../views/contents/About.vue';
import BizDetail from '../views/contents/biz_detail/BizDetail.vue';
import BizEvents from '../views/contents/biz_event/BizEvent.vue';
import BizReviews from '../views/contents/biz_review/BizReview.vue';
import BizEventUpdate from '../views/contents/biz_event/BizEventUpdate.vue';
import CustomerEvent from '../views/contents/customer_event/CustomerEvent.vue';
import CreateReview from '../views/contents/customer_review/CreateReview.vue';

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
    path: '/companies/:id',
    name: 'BizDetail',
    component: BizDetail
  },
  {
    path: '/business/:id/events',
    name: 'BusinessEvents',
    component: BizEvents
  },
  {
    path: '/business/:id/reviews',
    name: 'BusinessReviews',
    component: BizReviews
  },
  {
    path: '/edit-event/:id',
    name: 'BusinessEventUpdate',
    component: BizEventUpdate
  },
  {
    path: '/customer-events',
    name: 'CustomerEvents',
    component: CustomerEvent
  },
  {
    path: '/create-review/:eventId',
    name: 'CreateReview',
    component: CreateReview
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;