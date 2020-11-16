<template>
    <div v-if="list.length !== 0" class="overflow-auto" style="padding: 1%">
        <button class="btn btn-outline-primary" @click="getLists">새로고침</button>
        <div style="padding-top: 10px"></div>
        <b-table
            id="my-table"
            :items="list"
            :per-page="perPage"
            :current-page="currentPage"
            :fields="fields"
            small striped hover
            class="text-center"
        ></b-table>
        <b-pagination
            v-model="currentPage"
            :total-rows="list.length"
            :per-page="perPage"
            aria-controls="my-table"
            align="center"
        ></b-pagination>
    </div>
    <div v-else> 내용이 없습니다. </div>

</template>

<script>
import axios from 'axios';

export default {
    name: 'TestList',
    data() {
        return {
            perPage: 10,
            currentPage: 1,
            fields: [
                { key: 'requestId', label: 'id' },
                { key: 'goodName', label: '상품명' },
                { key: 'price', label: '상품금액' },
                { key: 'memberID', label: '판매자아이디' },
                { key: 'mobile', label: '구매자 휴대폰' },
                { key: 'createDate', label: '요청일시' },
                { key: 'paymentState', label: '결제 상태' },
            ],
            list: [],
        }
    },
    created() {
        this.getLists()
    },
    methods: {
        getLists() {
            axios
                .get("http://localhost:8080/list")
                .then(({data}) => {
                    this.list = data;
                })
        }
    }
}
</script>