<template>
    <div class="paging">
        <b-button-toolbar key-nav aria-label="Toolbar with button groups">
                <b-button-group class="before">
                <b-button @click="$emit('goBack')" :disabled="currentPage === 1">&lsaquo;</b-button>
            </b-button-group>
            <b-button-group class="current">
                <b-button>{{ currentPage }} / {{ totalPages }}</b-button>                
            </b-button-group>
            <b-button-group class="after">
                <b-button @click="$emit('goNext')" :disabled="currentPage === totalPages">&rsaquo;</b-button>
            </b-button-group>
        </b-button-toolbar>
    </div>
</template>
    
<script setup>
    import { defineProps, defineEmits } from 'vue';

    defineProps({
        currentPage: Number,
        totalPages: Number
    });

    defineEmits(['goBack', 'goNext']);
</script>

<style scoped>
    .paging {
        display: flex;
        justify-content: center;
        align-items: center;
        padding-bottom: 6rem;
    }
</style>