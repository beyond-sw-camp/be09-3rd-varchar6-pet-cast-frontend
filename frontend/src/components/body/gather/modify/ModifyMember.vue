<template>
    <b-form class="memberlist">
        <div>
            <b-row>
                <b-col v-for="member in members" :key="member.id" col="3" class="memberimg">
                    <div class="member-item">
                        <div class="member-role">{{ member.role }}</div>
                        <b-avatar :src="member.img" :text="member.initial" size="4rem"></b-avatar>
                        <!-- <div class="member-name">{{ member.name }}</div> -->
                        <div class="member-name">
                            <input 
                                v-model="member.name" 
                                @input="updateMemberName"
                                :placeholder="member.name"
                                class="name-input"
                                >
                        </div>
                    </div>
                </b-col>
            </b-row>
        </div>
    </b-form>
</template>

<script setup>
    defineProps({
        members:
        {
            type: Array,
            required: true
        }
    });
</script>

<style scoped>
    .memberlist {
        display: flex;
        justify-content: space-around;
        align-items: center;
        gap: 20px;
    }
    .memberimg {
        margin: 30px;
    }
    .member-item {
        display: flex;
        flex-direction: column;
        align-items: center;
    }

    .member-role {
        margin-bottom: 0.5rem;
    }

    .member-name {
        margin-top: 0.5rem;
        text-align: center; 
        display: flex;
        justify-content: center;
        align-items: center;
        font-weight: bold;
    }
    .name-input {
        width: 5em; 
    }
</style>