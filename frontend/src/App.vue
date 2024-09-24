<script setup>
import { RouterView } from 'vue-router';
import Header from './components/Header.vue';
import Footer from './components/Footer.vue';
import Navigation from './components/Navigation.vue';
</script>

<template>
  <div id="app">
    <header>
      <div class="wrapper">
        <Header />
        <Navigation />
      </div>
    </header>

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