<template>
    <section class="section">
        <div class="container" id="content">
            <div class="pagename">
                <ListTitle/>
                <ListBtn/>
            </div>
            <ListChart/>
        </div>
    </section>

</template>

<script setup>
    import ListTitle from '@/components/body/gather/ListTitle.vue';
    import ListChart from '@/components/body/gather/ListChart.vue';
    import ListBtn from '@/components/body/gather/ListBtn.vue';
</script>

<style scoped>
    .section{
        overflow: auto;
    }
    .container{
        display: flex;
        flex-direction: column;
        overflow: auto;
    }   
    
    .pagename {
        display: flex;
        padding-top: 2rem;
        color: #49D5FF;
        justify-content: space-between;
        font-family: 'Jua', sans-serif;
    }
</style>