import Home from './components/Home.vue';
import AlarmgatewayList from './components/gateway/AlarmgatewayList.vue';
import AlarmgatewayDetails from './components/gateway/AlarmgatewayDetails.vue';
import AlarmgatewayEdit from './components/gateway/AlarmgatewayEdit.vue';
import AlarmgatewayAdd from './components/gateway/AlarmgatewayAdd.vue';
import Alarmgateway from './components/gateway/Alarmgateway.vue';
import Label from './components/label/Label.vue';
import LabelAdd from './components/label/LabelAdd.vue';
import LabelList from './components/label/LabelList.vue';
import LabelEdit from './components/label/LabelEdit.vue';
import LabelDetails from './components/label/LabelDetails.vue';
import Location from './components/location/Location.vue';
import LocationList from './components/location/LocationList.vue';
import LocationEdit from './components/location/LocationEdit.vue';
import LocationDetails from './components/location/LocationDetails.vue';
import LocationAdd from './components/location/LocationAdd.vue';
import AlarmgatewayVueDt from './components/data/AlarmgatewayVueDt.vue';
import AlarmgatewayVuePage from './components/data/AlarmgatewayVuePage.vue';
import User from './components/user/User.vue';
import UserList from './components/user/UserList.vue';
import UserAdd from './components/user/UserAdd.vue';
import Charts from './components/user/Charts.vue';
import websocket from './components/websocket/websocket.vue';


export const routes = [
  { path: '', component: Home },
  { path: '/websocket', component: websocket },
  {
    path: "/charts",
    name:"charts",
    component: Charts,
  },
  {
    path: "/user",
    name:"user",
    component: User,
    children: [
      {
        path: '',
        component: UserList
      },
      {
        path: "add-user",
        name: "user-add",
        component: UserAdd,
        props: true
      },
    ]
  },
  /* LABEL ROUTES */
  {
    path: "/label",
    name: "label",
    component: Label,
    children: [
      {
        path: '',
        component: LabelList
      },
      {
        path: "add-label",
        name: "label-add",
        component: LabelAdd,
        props: true
      },
      {
        path: "label/:id/edit",
        name: "label-edit",
        component: LabelEdit,
        props: true
      },
      {
        path: "label/:id",
        name: "label-details",
        component: LabelDetails,
        props: true
      }
    ]
  },
  /* LOCATION ROUTES*/
  {
    path: "/location",
    name: "location",
    component: Location,
    children: [
      {
        path: '',
        component: LocationList
      },
      {
        path: "location/:id",
        name: "location-details",
        component: LocationDetails,
        props: true
      },
      {
        path: "location/:id/edit",
        name: "location-edit",
        component: LocationEdit,
        props: true
      },
      {
        path: "add-location",
        name: "location-add",
        component: LocationAdd,
        props: true
      }]
  },
  /* GATEWAY ROUTES*/
  {
    path: "/gateway",
    name: "alarmgateway",
    component: Alarmgateway,
    children: [
      {
        path: '',
        component: AlarmgatewayList
      },
      {
        path: "gateway/:id",
        name: "gateway-details",
        component: AlarmgatewayDetails,
        props: true
      },
      {
        path: "gateway/:id/edit",
        name: "gateway-edit",
        component: AlarmgatewayEdit,
        props: true
      },
      {
        path: "add-gateway",
        name: "gateway-add",
        component: AlarmgatewayAdd,
      }
    ]
  },
  {
    path: "/alarmgatewayVueDt",
    name: "alarmgatewayVueDt",
    component: AlarmgatewayVueDt,
  },
  {
    path: "/alarmgatewayVuePage",
    name: "alarmgatewayVuePage",
    component: AlarmgatewayVuePage,
  }

];