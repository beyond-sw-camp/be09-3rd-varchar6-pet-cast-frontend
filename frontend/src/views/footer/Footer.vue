<template>
  <footer>
    <FooterLeft/>
    <FooterRight/>
  </footer>
</template>

<script setup>
import FooterLeft from '../../components/footer/FooterLeft.vue';
import FooterRight from '../../components/footer/FooterRight.vue';
</script>

<style scoped>

footer {
  display: flex;
  justify-content: space-between;
  padding: 20px;
  background-color: #FFFED3;
  position: fixed;
  bottom: 0;
  width: 100%;
}
</style>