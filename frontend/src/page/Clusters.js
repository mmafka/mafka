import React from 'react';
import Header from "../thema/tema1/component/Header";
import Footer from "../thema/tema1/component/Footer";
import SideMenu from "../thema/tema1/component/SideMenu";
import PageContext from "../thema/tema1/component/PageContext";
import Properties from "../const/Properties";
import * as axios from "axios";
import CustomTable from "../thema/tema1/component/CustomTable"

var cevaplarRadioSonuc=new Map();
class Clusters extends React.Component {
    
    componentDidMount() {
        const script = document.createElement("script");
        script.src = "/thema/tema1/plugins/custom/tablerun.js";
        script.type = 'text/javascript';
        script.async = true;
        document.body.appendChild(script);
        this.handlePageClick({selected:0});
        this.getTotalRow();
    }
     constructor(props)
    {
        super(props);
        if(localStorage.getItem("token")=="")
        {
            window.location = '/login';
            return false;
        }
        
        this.state = {
            sinavKodu: '',
            cevaplarRadio:[],
            clusters:[],
            content:[],
            pageCount:0,
            totalRow:0,
            startRow:0,
            endRow:0,
            silId:0,
            duzenleId:0,
            silModal:'none',
            title:["Cluster Name","Node Count","Total CPU","Total Ram","Total Disk","Process"],
            showDuzenle:false,
            error: 0
        };
    }
    onChange = e => {
        this.setState({ [e.target.name]: e.target.value })
    }
    onValueChangeQuestion(event) {
        console.log(cevaplarRadioSonuc);
        cevaplarRadioSonuc[event.target.name]=event.target.value;
      }
    getTotalRow = ()=>{
        axios({
            method: 'get',
            url: Properties.baseUrl+'/cluster/count/',
            headers: {
                "Content-Type": "application/json",
                "Authorization" : "Bearer " + localStorage.getItem("token")
            }
        })
            .then(res => {
                this.setState({totalRow:res.data});
                this.setState({pageCount:res.data/10});
            });
    }
    handlePageClick=e=>{
        this.setState({ startRow: (e.selected*10)+1});
        this.setState({ endRow:((e.selected+1)*10)});
        axios({
            method: 'get',
            url: Properties.baseUrl+'/cluster/getAllDetails/'+e.selected,
            headers: {
                "Content-Type": "application/json",
                "Authorization" : "Bearer " + localStorage.getItem("token")
            }
        })
            .then(res => {
                var getClusters = [];
                res.data.forEach(function (eleman) {
                    var elemanIc = new Object;
                    elemanIc.clusterName = eleman.clusterName;
                    elemanIc.nodeCount = eleman.nodeCount;
                    elemanIc.totalRam = eleman.totalRam;
                    elemanIc.totalDisk = eleman.totalDisk;
                    elemanIc.totalCPU = eleman.totalCPU;
                    elemanIc.id = eleman.id;
                    getClusters.push(eleman);
                });
                this.setState({clusters:getClusters});
                let content = getClusters.map((item) =>
        (<tr>
                                    <td>{item.clusterName}</td>
                                    <td>{item.nodeCount}</td>
                                    <td>{item.totalCPU}</td>
                                    <td>{item.totalRam}</td>
                                    <td>{item.totalDisk}</td>
                                    <td>
                                        <button style={button} type="button" onClick={e => this.getPDF(item)} className="btn btn-block btn-outline-info">PDF İndir</button>
                                    </td>
                                </tr>));
                    this.setState({content:content});
            });
    }
    render() {

        return (
            <div>
                <Header/>
                <SideMenu clusters="active"/>
                <PageContext>
                    <div className="card card-primary">
                        <form>
                            <div className="card-footer">
                                <button  type="button" onClick={e => this.sendCevapGirisi()}   className="btn btn-primary m-2">Create New Cluster</button>

                                <button  type="button" onClick={e => this.sendCevapGirisi()}   className="btn btn-primary">Add Exists Cluster</button>
                            </div>
                        </form>
                    </div>
                    <div className="card">
                        <div className="card-header">
                            <h3 className="card-title">Clusters</h3>
                        </div>
                        <div className="card-body">
                        <CustomTable state={this.state} getTotalRow={this.getTotalRow} handlePageClick={this.handlePageClick}></CustomTable>
                        </div>
                    </div>
                </PageContext>
                <Footer/>
            </div>);
    }
}
const marginAllWidth = {
    margin: "5px",
    marginLeft: "30px",
    width: "20px"
};
const button = {
    marginTop: "0px",
    marginLeft: "10px",
    float: "left",
    width: "100px"
};

const marginAll = {
    margin: "5px",
    marginLeft: "30px"
};
export default Clusters;
