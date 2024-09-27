<template>
    <section class="container">
        <CreateTitle/>
        <CreateInfo/>
        <CreateDescription/>
    </section>
</template>

<script setup>
    import CreateTitle from '@/components/body/gather/create/CreateTitle.vue';
    import CreateInfo from '@/components/body/gather/create/CreateInfo.vue';
    import CreateDescription from '@/components/body/gather/create/CreateDescription.vue';

</script>

<style scoped>
    .container {
        overflow: auto;
        height: 140vh;
    }
</style>