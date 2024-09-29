<script setup>
import { provide, ref } from 'vue';
import { RouterView } from 'vue-router';
import Header from './views/header/header/Header.vue';
import Footer from './views/footer/Footer.vue';
import Navigation from './views/header/navigation/Navigation.vue';

const userId = ref('');
const isLoggedIn = ref(false);

provide('userId', userId);
provide('setUserId', (value) => {
  userId.value = value;
})
provide('isLoggedIn', isLoggedIn);
provide('setIsLoggedIn', (value) => {
  isLoggedIn.value = value;
});

if (localStorage.getItem('userId')) {
  userId.value = localStorage.getItem('userId');
}

if (localStorage.getItem('accessToken')) {
  isLoggedIn.value = true;
}

</script>

<template>
  <div id="app">
    <header>
      <div class="wrapper">
        <Header/>
      </div>
    </header>
    
    <Navigation />

    <RouterView />

    <Footer/>
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