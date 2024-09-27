<template>
    <section class="section">
        <div class="container">
            <ListTitle/>
            <ListBtn/>
            <ListChart/>
        </div>
    </section>

</template>

<script setup>
    import ListTitle from '@/components/body/gather/list/ListTitle.vue';
    import ListChart from '@/components/body/gather/list/ListChart.vue';
    import ListBtn from '@/components/body/gather/list/ListBtn.vue';
</script>

<style scoped>
    .section{
        overflow: auto;
    }
    .container{
        display: flex;
        flex-direction: column;
    }   
</style>