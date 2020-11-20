<template>
    <div style="padding: 1%">
        <button class="btn btn-outline-primary" @click="getLists">새로고침</button>
        <div v-if="list.length !== 0" class="overflow-auto">
            <div style="padding-top: 10px"></div>
            <b-table
                id="my-table"
                :items="list"
                :per-page="perPage"
                :current-page="currentPage"
                :fields="fields"
                small striped hover
                class="text-center"
            >
                <template #cell(paymentState)="item">
                    <b class="text-info">{{ item.item.paymentState }}</b>
                </template>

                <template #cell(button)="item">
                    <button v-if="item.item.paymentState === 'REQUEST'" class="btn btn-sm btn-primary">결제하기</button>{{' '}}
                    <button v-if="item.item.paymentState === 'REQUEST'" class="btn btn-sm btn-outline-primary">결제 요청 취소</button>{{' '}}
                    <button v-if="item.item.paymentState === 'COMPLETE'" class="btn btn-sm btn-danger">결제 취소</button>
                    <button v-if="item.item.paymentState === 'ADJUST'" class="btn btn-sm">정산된 결제 취소</button>
                </template>

            </b-table>
            <b-pagination
                v-model="currentPage"
                :total-rows="list.length"
                :per-page="perPage"
                aria-controls="my-table"
                align="center"
            ></b-pagination>
        </div>
        <div v-else><br/> 내용이 없습니다.</div>
    </div>
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
                {key: 'requestId', label: 'id'},
                {key: 'goodName', label: '상품명'},
                {key: 'price', label: '상품금액'},
                {key: 'memberID', label: '판매자아이디'},
                {key: 'mobile', label: '구매자 휴대폰'},
                {key: 'createDate', label: '요청일시'},
                {key: 'orderNo', label: '주문 번호'},
                {key: 'feedbackToken', label: '결제 요청 취소 토큰'},
                {key: 'paymentState', label: '결제 상태'},
                {key: 'button', label: ''},
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
                .get("http://localhost:8080/p/list/request")
                .then(({data}) => {
                    this.list = data;
                })
        },
    }
}
</script>

<style scoped>

</style>
