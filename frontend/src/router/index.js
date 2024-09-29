import { createRouter, createWebHistory } from 'vue-router';
import Home from '../views/contents/Home.vue';
import About from '../views/contents/About.vue';
import Login from '../views/contents/Login.vue';
import BizDetail from '../views/contents/biz/BizDetail.vue';
import MyPage from '../views/contents/my_page/MyPage.vue';
import BizEvents from '../views/contents/biz_event/BizEvent.vue';
import BizReviews from '../views/contents/biz_review/BizReview.vue';
import BizEventUpdate from '../views/contents/biz_event/BizEventUpdate.vue';
import CustomerEvent from '../views/contents/customer_event/CustomerEvent.vue';
import CustomerReview from '../views/contents/customer_review/CustomerReview.vue';
import CreateReview from '../views/contents/customer_review/CreateReview.vue';
import UpdateReview from '../views/contents/customer_review/UpdateReview.vue';
import BizList from '../views/contents/biz/BizList.vue';
import BizMypage from '../views/contents/biz/BizMypage.vue';
import Signup from '../views/contents/signup/Signup.vue';
import RequestWrite from '../views/request/RequestWrite.vue';
import RequestList from '../views/request/RequestList.vue';
import GatherList from '../views/contents/Gather/GatherList.vue';
import GatherDetail from '../views/contents/Gather/GatherDetail.vue';
import GatherCreate from '../views/contents/Gather/GatherCreate.vue';
import GatherModify from '../views/contents/Gather/GatherModify.vue';
import GatherDelete from '../views/contents/Gather/GatherDelete.vue';
import GatherInvitation from '../views/contents/Gather/GatherInvitation.vue';

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
    path: '/business-event-update/:id',
    name: 'BizEventUpdate',
    component: BizEventUpdate
  },
  {
    path: '/customer-events',
    name: 'CustomerEvents',
    component: CustomerEvent
  },
  {
    path: '/customer-reviews',
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
  },
  {
    path: '/login',
    component: Login
  },
  {
    path: '/signup',
    component: Signup
  },
  {
    path: '/biz-list',
    name: 'BizList',
    component: BizList
  },
  {
    path: '/me',
    name: 'MyPage',
    component: MyPage,
  },
  {
    path: '/biz-mypage',
    name: 'BizMypage',
    component: BizMypage,
  },
  {
    path: '/gatherlist',
    name: 'gatherlist',
    component: GatherList
  },
  {
    path: '/gatherdetail/:id',
    name: 'gatherdetail',
    component: GatherDetail
  },
  {
    path: '/gathercreate',
    name: 'gathercreate',
    component: GatherCreate
  },
  {
    path: '/gathermodify/:id',
    name: 'gathermodify',
    component: GatherModify
  },
  {
    path: '/gatherdelete/:id',
    name: 'gatherdelete',
    component: GatherDelete
  },
  {
    path: '/gatherinvitation',
    name: 'gatherinvitation',
    component: GatherInvitation
  }

  // 요청하지 않은 페이지
  {
    path: "/:catchAll(.*)*",
    component: () => import("../views/contents/ErrorNotFound.vue"),
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;
