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
import Datatable from './components/data/Datatable.vue';
import Datatable2 from './components/data/Datatable2.vue';
import AlarmgatewayVueDt from './components/data/AlarmgatewayVueDt.vue';
import AlarmgatewayJsDt from './components/data/AlarmgatewayJsDt.vue';
import AlarmgatewayVuePage from './components/data/AlarmgatewayVuePage.vue';


export const routes = [
  { path: '', component: Home },
  /* LABEL ROUTES */
  {
    path: "/label",
    name: "label",
    component: Label,
    children: [
      {path: '',
      component: LabelList},
      {
        path: "add-label",
        name: "label-add",
        component: LabelAdd,
        props:true
      },
      {
        path: "label/:id/edit",
        name: "label-edit",
        component: LabelEdit,
        props:true
      },
      {
        path: "label/:id",
        name: "label-details",
        component: LabelDetails,
        props:true
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
      path: "/datatables",
      name: "datable",
      component: Datatable,
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
},
  {
      path: "/alarmgatewayJsDt",
      name: "alarmgatewayJsDt",
      component: AlarmgatewayJsDt,
  },
  {
      path: "/datatables2",
      name: "datable2",
      component: Datatable2,
  }
  
];