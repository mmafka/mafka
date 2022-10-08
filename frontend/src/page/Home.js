import React from 'react';
import Header from "../thema/tema1/component/Header";
import Footer from "../thema/tema1/component/Footer";
import SideMenu from "../thema/tema1/component/SideMenu";
import PageContext from "../thema/tema1/component/PageContext";

const active = {Home: "active"};
class Home extends React.Component {
    componentDidMount() {

    }
    constructor(props)
    {
        super(props);
        if(localStorage.getItem("token")=="")
        {
            window.location = '/login';
            return false;
        }
    }
    render(props) {
        return (
            <div>
                <Header/>
                <SideMenu home="active"/>
                <PageContext>
                    <div className="row">
                        <div className="col-6 col-md-3 text-center">
                            <input type="text" className="knob" value="30" data-width="90" data-height="90"
                                   data-fgColor="#3c8dbc" disabled/>

                                <div className="knob-label">HEDEF TAMAMLAMA DÜZEYİ</div>
                        </div>
                        <div className="col-6 col-md-3 text-center">
                            <input type="text" className="knob" value="30" data-width="90" data-height="90"
                                   data-fgColor="#f56954" disabled/>

                                <div className="knob-label">MATEMATİK TAMAMLAMA DÜZEYİ</div>
                        </div>
                        <div className="col-6 col-md-3 text-center">
                            <input type="text" className="knob" value="15" data-min="-150" data-max="150"
                                   data-width="90"
                                   data-height="90" data-fgColor="#00a65a" disabled/>

                                <div className="knob-label">FİZİK TAMAMLAMA DÜZEYİ</div>
                        </div>
                        <div className="col-6 col-md-3 text-center">
                            <input type="text" className="knob" value="55" data-width="90" data-height="90"
                                   data-fgColor="#2b3bb0" disabled/>

                                <div className="knob-label">TÜRKÇE TAMAMLAMA DÜZEYİ</div>
                        </div>
                    </div>
                    <div className="row">
                        <div className="col-6 col-md-3 text-center">
                            <input type="text" className="knob" value="88" data-width="90" data-height="90"
                                   data-fgColor="#9f1a45" disabled/>

                            <div className="knob-label">KİMYA TAMAMLAMA DÜZEYİ</div>
                        </div>
                        <div className="col-6 col-md-3 text-center">
                            <input type="text" className="knob" value="80" data-width="90" data-height="90"
                                   data-fgColor="#a761ca" disabled/>

                            <div className="knob-label">TARİH TAMAMLAMA DÜZEYİ</div>
                        </div>
                        <div className="col-6 col-md-3 text-center">
                            <input type="text" className="knob" value="12" data-min="-150" data-max="150"
                                   data-width="90"
                                   data-height="90" data-fgColor="#d2ae2b" disabled/>

                            <div className="knob-label">COĞRAFYA TAMAMLAMA DÜZEYİ</div>
                        </div>
                        <div className="col-6 col-md-3 text-center">
                            <input type="text" className="knob" value="23" data-width="90" data-height="90"
                                   data-fgColor="#00c0ef" disabled/>

                            <div className="knob-label">BİOLOJİ TAMAMLAMA DÜZEYİ</div>
                        </div>
                    </div>
                </PageContext>
                <Footer/>
            </div>);
    }
}


export default Home;
