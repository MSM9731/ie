import React, { useContext, useEffect, useState } from 'react';
import { getCommodities } from '../../services/commodities';
import Pagination from '@mui/material/Pagination';
import Stack from '@mui/material/Stack';
import { context } from '../context/contex';

const Home = () => {
    const searchContext = useContext(context);

    const {
      tempCommodities,
      setCommodities,
      setTempCommodities,
      perPage,
      currentPage,
      handlePageChange,
      countPages,
      setCountPages
    } = searchContext;
    useEffect(() => {
        (async () => {
          await getCommodities()
            .then((ans) => {
              console.log(ans.data);
              var data = [...ans.data];
              setCommodities(data);
              setTempCommodities(data);
              setCountPages(Math.ceil(data.length / perPage));
            })
            .catch((err) => {});
        })();
      }, []);
    
    return (
        <div class="container mb-4">
            <div class="row mycard text-center home-bar">
                <div class="col-12 d-flex flex-wrap text-brown p-2">
                    <div class="col-12 col-sm-6 col-lg-3 p-3">
                        <p class="fw-bold mb-3 f-16px m-2">

                            Available commodities
                        </p>
                    </div>
                    <div class="col-12 col-sm-6 col-lg-3 p-3" >
                        <div class="form-check form-switch m-1">
                            <input class="form-check-input switch"  type="checkbox" role="switch" id="flexSwitchCheckDefault"/>

                        </div>
                    </div>
                

                    <div class="col-12 col-sm-6 col-lg-3 p-1 home-bar2">
                        <label class="fw-bold m-3">sort by:</label>
                        <button class="btn btn-light-brown2">name</button>
                        <button class="btn  btn-light-brown3">price</button>

                    </div>

                </div>
            </div>
        <div class="row">

                <div class="col-12 d-flex flex-wrap text-brown p-2">

                  

            {tempCommodities.map((commodity, index) => {
                if (perPage * currentPage - perPage <= index && index < perPage * currentPage) {

                        return(
                            <div class="col-12 col-sm-6 col-lg-3 p-3">
                            <div class="card shadow-sm border-0">
                                <div class="card-body px-4">
                                    <div class="">
                                        <h3 class="card-title">{commodity.name}</h3>
                                        <p class="card-subtitle small text-red">{commodity.inStock} left in stock</p>
                                    </div>
                                </div>
                                <div class="py-2">
                                    <img src={commodity.image} alt="image5" class="card-img-top" height="200" />
                                </div>
                                <div class="card-body px-4">
                                    <div class="hstack flex--wrap justify-content-between">
                                        <h4 class="card-title mb-0">{commodity.price}$</h4>
                                        <button type="button" class="btn btn-sm btn-outline-brown p-0 px-2"> add to cart</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        )
                    }
                })
            }
        

            </div>
        </div>

            

            <div className='h-100 d-flex align-items-center justify-content-center'>
            <Pagination count={countPages} defaultPage={1} siblingCount={1}  page={currentPage} onChange={handlePageChange}/>
            {/* <Pagination count={countPages} page={currentPage} onChange={handlePageChange} /> */}
            </div>
        </div>
    );
}

export default Home;
