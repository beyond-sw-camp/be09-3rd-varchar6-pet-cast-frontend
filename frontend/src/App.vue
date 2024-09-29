<script setup>
import { RouterView } from 'vue-router';
import Header from './views/header/header/Header.vue';
import Footer from './views/footer/Footer.vue';
import Navigation from './views/header/navigation/Navigation.vue';
import qna from './views/qna/QnaList.vue';
</script>

<template>
  <div id="app">
    <header>
      <div class="wrapper">
        <Header />
      </div>
    </header>
    
    <Navigation />
    
    <RouterView />

    <Footer />
  </div>
</template>

<style scoped>
header {
  line-height: 1.5;
}

.logo {
  display: block;
  margin: 0 auto 2rem;
}


</style>