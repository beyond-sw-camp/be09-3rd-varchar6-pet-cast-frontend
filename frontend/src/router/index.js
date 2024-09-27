import { createRouter, createWebHistory } from 'vue-router';
import Home from '../views/contents/Home.vue';
import About from '../views/contents/About.vue';
import BizDetail from '../views/contents/biz_detail/BizDetail.vue';
import BizEvents from '../views/contents/biz_event/BizEvent.vue';
import BizReviews from '../views/contents/biz_review/BizReview.vue';
import BizEventUpdate from '../views/contents/biz_event/BizEventUpdate.vue';
import CustomerEvent from '../views/contents/customer_event/CustomerEvent.vue';
import CustomerReview from '../views/contents/customer_review/CustomerReview.vue';
import CreateReview from '../views/contents/customer_review/CreateReview.vue';
import UpdateReview from '../views/contents/customer_review/UpdateReview.vue';

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
    path: '/biz-detail/:id',
    name: 'BizDetail',
    component: BizDetail
  },
  {
    path: '/biz-events/:id',
    name: 'BizEvents',
    component: BizEvents
  },
  {
    path: '/biz-reviews/:id',
    name: 'BizReviews',
    component: BizReviews
  },
  {
    path: '/biz-event-update/:id',
    name: 'BizEventUpdate',
    component: BizEventUpdate
  },
  {
    path: '/customer-events/:id',
    name: 'CustomerEvents',
    component: CustomerEvent
  },
  {
    path: '/customer-reviews/:id',
    name: 'CustomerReview',
    component: CustomerReview
  },
  {
    path: '/create-review/:eventId',
    name: 'CreateReview',
    component: CreateReview
  },
  {
    path: '/update-review/:reviewId',
    name: 'UpdateReview',
    component: UpdateReview
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;