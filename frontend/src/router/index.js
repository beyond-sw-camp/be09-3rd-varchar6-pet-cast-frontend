import { createRouter, createWebHistory } from 'vue-router';
import Home from '../views/contents/Home.vue';
import About from '../views/contents/About.vue';
import BizDetail from '../views/contents/biz_detail/BizDetail.vue';
import BusinessEvents from '../views/contents/biz_detail/BusinessEvents.vue';
import BusinessReviews from '../views/contents/biz_detail/BusinessReviews.vue';
import BusinessQA from '../views/contents/biz_detail/BusinessQA.vue';
import ReportList from '@/views/report/ReportList.vue';
import ReportPost from '@/views/report/ReportPost.vue';
import ReportDetail from '@/views/report/ReportDetail.vue';

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
  {
    path: '/api/v1/report',
    name: 'ReportList',
    component: ReportList
  },
  {
    path: '/api/v1/report/post',
    name: 'ReportPost',
    component: ReportPost
  },
  {
    path: '/api/v1/report/:id',
    name: 'ReportDetail',
    component: ReportDetail
  }

];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;