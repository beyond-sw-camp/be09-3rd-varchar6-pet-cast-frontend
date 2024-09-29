<template>
    <div class="members-container">
        <div v-for="(member, index) in members" :key="index" class="plus-icon">
            <div class="avatar" @click="openPopup(index)">
                <img :src="member.image" :alt="`Member ${index + 1} image`" />
            </div>
        </div>
    </div>
</template>

<script setup>
    import { ref } from 'vue';

    const defaultImage = '/src/assets/icon/gather/plusmember.png';

    const members = ref([
        { image: defaultImage },
        { image: defaultImage },
        { image: defaultImage },
        { image: defaultImage }
    ]);

    const openPopup = (index) => {
        const width = 700;
        const height = 700;
        const left = (window.screen.width / 2) - (width / 2);
        const top = (window.screen.height / 2) - (height / 2);

        window.open(
            '/gatherinvitation', 
            `MemberImage${index}`,
            `width=${width},height=${height},left=${left},top=${top},resizable=yes,scrollbars=yes`
        );
    };
</script>

<style scoped>
    .members-container {
        display: flex;
        justify-content: space-around;
        flex-wrap: wrap;
    }
    .plus-icon {
        cursor: pointer;
        margin: 10px;
    }
    .avatar img {
        width: 4rem;
        height: 4rem;
        object-fit: cover;
        border-radius: 50%;
    }
</style>