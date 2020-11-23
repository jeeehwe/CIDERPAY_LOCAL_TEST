<template>
    <div style="padding: 1%">
        <h3>결제 요청 리스트</h3>
        <button class="btn btn-outline-primary" @click="getLists">새로고침</button>{{' '}}
        <button class="btn btn-primary" @click="$router.push('/list/payment')">결제 리스트</button>
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
                    <button v-if="item.item.paymentState === 'REQUEST'" class="btn btn-sm btn-primary" @click="makePayment(item.item)">결제하기</button>{{' '}}
                    <button v-if="item.item.paymentState === 'REQUEST'" class="btn btn-sm btn-outline-primary" @click="paymentRequestCancel(item.item)">결제 요청 취소</button>{{' '}}
                    <button v-if="item.item.paymentState === 'COMPLETE'" class="btn btn-sm btn-outline-success" @click="$router.push('/list/payment')">결제 리스트 보기</button>{{' '}}
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
import api from "@/api/api";
import view from "@/api/view";

export default {
    name: 'RequestList',
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
        paymentRequestCancel(item) {
            api.paymentRequestCancel({
                memberID: item.memberID,
                payUniqueNo: item.payUniqueNo
            }, () => {
                alert('결제 요청이 취소되었습니다.');
                this.getLists();
            })
        },
        makePayment(item) {
            view.makePayment(item, () => this.getLists())
        }
    }
}
</script>

<style scoped>

</style>
