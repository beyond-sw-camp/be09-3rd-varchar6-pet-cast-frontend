<template>
    <section class="container">
        <CreateTitle/>
        <CreateInfo/>
        <CreateDescription/>
        <CreateBtn/>
    </section>
</template>

<script setup>
    import CreateTitle from '@/components/body/gather/create/CreateTitle.vue';
    import CreateInfo from '@/components/body/gather/create/CreateInfo.vue';
    import CreateDescription from '@/components/body/gather/create/CreateDescription.vue';
    import CreateBtn from '@/components/body/gather/create/CreateBtn.vue';

</script>

<style scoped>
    .container {
        overflow: auto;
        height: 140vh;
    }
</style>