import React from 'react';
import { withRouter } from "react-router-dom";
import { getCommodities } from '../../services/commodities';

const Product = ({match}) => {
    


    // const handleLogin = async (event) => {
    //   const user = { username, password };
    //   try {
    //     if (validator.current.allValid()) {
    //       const { status, data } = await loginUser(user);
    //       if (status === 200) {
    //         console.log(data);
    //         successMessage(`Hi ${username}:)`);
    //         localStorage.setItem("user", btoa(username));
    //         history.replace("/home");
    //       } else {
    //         errorMessage("Somthings wron...");
    //       }
    //     } else {
    //       validator.current.showMessages();
    //       forceUpdate(1);
    //     }
    //   } catch (ex) {
    //     if (ex.response.status == 400) {
    //       const register = ex.response.data;
    //       console.log(register);
    //       errorMessage(register.data);
    //     }
    //   }
    // };
    return (
        <div class="container">
        <div class="row py-5">
            <div class="col-12 my-5 text-brown d-flex flex-wrap-reverse  border-2">
                <div class=" col-12 col-md-6 px-4 px-md-0 mt-5 mt-md-0 border-">
                    <img src="/img/phone.png" height="100%" alt="product image" class="w-500px"/>
                </div>
                <div class="col-12 col-md-6  border-">
                    <h1 class="display-1 fw-bold mb-3 f-40px">Huawei nova 9</h1>

                    <div class="f-24px">
                        <div class="row">
                            <div class="col-8">
                                <p class="text-red">5 left in style</p>
                            </div>
                            <div class="col" id="star-rate">
                                <img src="/icon/star.png" alt=""/>

                            </div>
                            <div class="col">
                                <p class="fw-bold">4.1<i class="text-gary f-12px">(12)</i></p>
                            </div>

                        </div>

                        <p class="fw-bold text-black">by <a href="#">Huawei</a></p>
                        <p>Category(s)</p>
                        <ul>
                            <li>Technologhy</li>
                            <li>IT</li>
                        </ul>
                    </div>
                    <div class=" mycard p-1">
                        <div class="row">

                            <div class="col">
                                <p class="fw-bold f-36px price">300$</p>

                            </div>
                            <div class="col-4">
                                <button class="btn btn-lg btn-outline-brown2 text-brown">add to cart</button>

                            </div>

                        </div>
                    </div>
                    <div class="container">
                        <div class="row justify-content-start mt-4">
                            <div class="col-12">
                                <p class="fw-bold text-black f-24px m-2">rate now</p>
                                <img src="/img/star.png" />
                                <img src="/img/star.png" />
                            </div>

                        </div>
                        <div class="row justify-content-end">

                            <div class="col-3">
                                <button class="btn  btn-brown submit">submit</button>

                            </div>
                        </div>
                        <div>

                        </div>

                    </div>

                </div>

            </div>
            <div class="container">
                <div class="mycard p-4">


                    <div class="row  justify-content-start">
                        <div class="col-4">
                            <p class="fw-bold500 text-brown f-24px">Comments<i class="text-gary f-12px">(2)</i></p>
                        </div>
                    </div>
                    <div class="row  justify-content-start">
                        <div class="col">
                            <p class="fw-bold500 text-black f-36px">This was awsome!!!!</p>
                        </div>
                    </div>
                    <div class="row  justify-content-start">
                        <div class="col">
                            <p>
                                <i class="text-gary f-12px">2023-03-20</i>&nbsp; &nbsp;<i
                                    class="text-gary fw-bold f-24px">.</i>&nbsp; &nbsp; <i
                                    class="text-gary f-12px">#username</i>
                            </p>
                        </div>
                    </div>
                    <div class="row justify-content-end">
                        <div class="col-4">
                            <p class="text-brown">Is this comment helpful? &nbsp; &nbsp;<i
                                    class="ico-like text-black">1</i>&nbsp; &nbsp;&nbsp; &nbsp;<i
                                    class="ico-dislike text-black">1</i></p>
                        </div>
                    </div>
                    <hr />
                    <div class="row  justify-content-start">
                        <div class="col">
                            <p class="fw-bold500 text-black f-36px">This was awfulllllllllllllllllll!!!!</p>
                        </div>
                    </div>
                    <div class="row  justify-content-start">
                        <div class="col">
                            <p>
                                <i class="text-gary f-12px">2023-03-20</i>&nbsp; &nbsp;<i
                                    class="text-gary fw-bold f-24px">.</i>&nbsp; &nbsp; <i
                                    class="text-gary f-12px">#username</i>
                            </p>
                        </div>
                    </div>
                    <div class="row justify-content-end">
                        <div class="col-4">
                            <p class="text-brown">Is this comment helpful? &nbsp; &nbsp;<i
                                    class="ico-like text-black">1</i>&nbsp; &nbsp;&nbsp; &nbsp;<i
                                    class="ico-dislike text-black">1</i></p>
                        </div>
                    </div>
                    <hr />
                    <div class="row ">
                        <div class="col-4">
                            <p class="fw-bold500 text-brown f-24px"><i>Submit your opinion</i></p>
                        </div>
                    </div>
                    <div class="row">

                        <div class="col-10">
                            <textarea class="form-control bg-light-gray" id="exampleFormControlTextarea1"
                                rows="3"></textarea>
                        </div>
                    </div>
                    <div class="row justify-content-end">

                        <div class="col-2 margin-top-40">
                            <button class="btn  btn-brown ">Post</button>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-12 d-flex flex-wrap text-brown p-2">

                <div class="col-12 col-sm-6 col-lg-3 p-3">
                    <div class="card shadow-sm border-0">
                        <div class="card-body px-4">
                            <div class="">
                                <h3 class="card-title">Huawei nova 9</h3>
                                <p class="card-subtitle small text-red">1 left in stock</p>
                            </div>
                        </div>
                        <div class="py-2">
                            <img src="/img/phone.png" alt="image5" class="card-img img-fluid mh-300px"/>
                        </div>
                        <div class="card-body px-4">
                            <div class="hstack flex-wrap justify-content-between">
                                <h3 class="card-title mb-0">300$</h3>
                                <button type="button" class="btn btn-outline-brown py-1"> add to cart</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-12 col-sm-6 col-lg-3 p-3">
                    <div class="card shadow-sm border-0">
                        <div class="card-body px-4">
                            <div class="">
                                <h3 class="card-title">Huawei nova 9</h3>
                                <p class="card-subtitle small text-red">1 left in stock</p>
                            </div>
                        </div>
                        <div class="py-2">
                            <img src="/img/phone.png" alt="image5" class="card-img img-fluid mh-300px"/>
                        </div>
                        <div class="card-body px-4">
                            <div class="hstack flex-wrap justify-content-between">
                                <h3 class="card-title mb-0">300$</h3>
                                <button type="button" class="btn btn-outline-brown py-1"> add to cart</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-12 col-sm-6 col-lg-3 p-3">
                    <div class="card shadow-sm border-0">
                        <div class="card-body px-4">
                            <div class="">
                                <h3 class="card-title">Huawei nova 9</h3>
                                <p class="card-subtitle small text-red">1 left in stock</p>
                            </div>
                        </div>
                        <div class="py-2">
                            <img src="/img/phone.png" alt="image5" class="card-img img-fluid mh-300px"/>
                        </div>
                        <div class="card-body px-4">
                            <div class="hstack flex-wrap justify-content-between">
                                <h3 class="card-title mb-0">300$</h3>
                                <button type="button" class="btn btn-outline-brown py-1"> add to cart</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-12 col-sm-6 col-lg-3 p-3">
                    <div class="card shadow-sm border-0">
                        <div class="card-body px-4">
                            <div class="">
                                <h3 class="card-title">Huawei nova 9</h3>
                                <p class="card-subtitle small text-red">1 left in stock</p>
                            </div>
                        </div>
                        <div class="py-2">
                            <img src="/img/phone.png" alt="image5" class="card-img img-fluid mh-300px"/>
                        </div>
                        <div class="card-body px-4">
                            <div class="hstack flex-wrap justify-content-between">
                                <h3 class="card-title mb-0">300$</h3>
                                <button type="button" class="btn btn-outline-brown py-1"> add to cart</button>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </div>
    </div>
    );
}

export default withRouter(Product);
