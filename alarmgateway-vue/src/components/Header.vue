<template>
    <ul class="nav nav-pills">
        <router-link to="/" tag="li" active-class="active" exact><a>home</a></router-link>
        <router-link to="/alarmgatewayVuePage" tag="li" active-class="active"><a>gateway vue pageable</a></router-link>
        <router-link to="/alarmgatewayVueDt" tag="li" active-class="active"><a>gateway vue datatable</a></router-link>
        <router-link to="/user" tag="li" active-class="active"><a>user datatable</a></router-link>
        <router-link to="/gateway" tag="li" active-class="active"><a>gateway</a></router-link>
        <router-link to="/label" tag="li" active-class="active"><a>label</a></router-link>
        <router-link to="/location" tag="li" active-class="active"><a>location</a></router-link>
        <router-link to="/charts" tag="li" active-class="active"><a>charts</a></router-link>
        <router-link to="/websocket" tag="li" active-class="active"><a>websockets</a></router-link>
    </ul>
</template>
