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
  padding: 10px;
  background-color: #FFFED3;
  bottom: 0;
  width: 100%;
  height: 10%;
  left: 0;
}
</style>