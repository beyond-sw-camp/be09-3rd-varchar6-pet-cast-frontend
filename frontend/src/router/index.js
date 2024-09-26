import { createRouter, createWebHistory } from 'vue-router';
import Home from '../views/contents/Home.vue';
import About from '../views/contents/About.vue';
import qna from '@/views/qna/qna.vue';
import QnaList from '@/views/qna/components/qnaList.vue';
import QnaPost from '@/views/qna/QnaPost.vue';
import QnaRead from '@/views/qna/QnaRead.vue';
// import QnAList from '../components/QnAList.vue'
// import QnADetail from '../components/QnADetail.vue'
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
    path: '/qna',
    redirect: '/qnalist'
  },
  // {
  //   // path: 'api/v1/qna',
  //   path: '/qna',
  //   component: qna,
  //   name: 'Qna',
  // },
  {
    path: '/qna-list',
    component: QnaList
  },
  {
    path: '/qna-post',
    component: QnaPost
  },
  {
    path: '/qnaread/:id',
    component: QnaRead
  }
  
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;