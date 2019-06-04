<template>
    <ul class="nav nav-pills">
        <router-link to="/" tag="li" active-class="active" exact><a>home</a></router-link>
        <router-link to="/gateway" tag="li" active-class="active"><a>gateway</a></router-link>
        <!-- <router-link to="/datatables" tag="li" active-class="active"><a>data</a></router-link> -->
        <router-link to="/alarmgatewayVuePage" tag="li" active-class="active"><a>gateway vue pageable</a></router-link>
        <router-link to="/alarmgatewayJsDt" tag="li" active-class="active"><a>gateway js datatable</a></router-link>
        <router-link to="/alarmgatewayVueDt" tag="li" active-class="active"><a>gateway vue datatable</a></router-link>
        <!-- <router-link to="/datatables2" tag="li" active-class="active"><a>data2</a></router-link> -->
        <router-link to="/label" tag="li" active-class="active"><a>label</a></router-link>
        <router-link to="/location" tag="li" active-class="active"><a>location</a></router-link>
    </ul>
</template>
