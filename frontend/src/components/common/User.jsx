import React from 'react';
import { Link } from 'react-router-dom/cjs/react-router-dom.min';

const User = () => {
  return (
    <div class="container -fluid">
      <div class="row py-5">
        <div class="mycard col-12 my-5 text-brown d-flex flex-wrap-reverse py-4 px-4">
          <div class="contact-info col-12 col-lg-6 px-4 px-lg-0 mt-5 mt-lg-0  fs-28px fw-600 overflow-hidden">
            <p>
              <img class="me-3" src="./icon/user.svg" alt="user-icon" width="20" height="20" />
              Marshal
            </p>
            <p>
              <img class="me-3" src="./icon/mail.svg" alt="mail-icon" width="20" height="20" />
              Marshal.Marshals@gmail.com
            </p>
            <p>
              <img class="me-3" src="./icon/calendar.svg" alt="calendar-icon" width="20" height="20" />
              1972/10/17
            </p>
            <p>
              <img class="me-3" src="./icon/location.svg" alt="location-icon" width="20" height="20" />
              Lorem ipsum dolor sit amet
            </p>
            <div className='mt-6'>
              <Link to="/logout" type="button" class="btn text-bg-brown " >logout</Link>
            </div>
          </div>
          <div class="col-12 col-lg-6 text-center ">
            <h1 class="total-price fw-bold mb-3">
              <span>$</span>10000000
            </h1>
            <p>
              <input class="form-control text-center text-grey border-grey w-727px mw-100 rounded-3" placeholder="$Amount" />
            </p>
            <p><button type="button" class="btn text-bg-brown w-727px mw-100" data-toggle="modal" data-target="#yourCart">Add More Credit</button></p>
          </div>
        </div>
        <div class="col-12 my-5 text-brown">
          <h3 class="mb-4 cart-text">
            <img src="./icon/cart.svg" alt="cart-icon" width="32" height="32" />
            Cart
          </h3>
          <div class="table-responsive ">
            <div class="table fs-28px text-center border-">
              <div class="table-head text-dark col-12">
                <div class="tr mycard hstack col-12 py-3 px-4 my-4">
                  <span class="th col">Image</span>
                  <span class="th col">Name</span>
                  <span class="th col">Categories</span>
                  <span class="th col">Price</span>
                  <span class="th col">Provider ID</span>
                  <span class="th col">Rating</span>
                  <span class="th col">In Stock</span>
                  <span class="th col">In Cart</span>
                </div>
              </div>
              <div class="table-body text-grey col-12">
                <div class="tr mycard hstack col-12 py-3 px-4 my-4">
                  <span class="td colblock ">
                    <img src="./img/phone.png" alt="" width="106" height="102" />
                  </span>
                  <span class="td col">Galaxy S21</span>
                  <span class="td col">Technology, Phone</span>
                  <span class="td col">$21000000</span>
                  <span class="td col">1234</span>
                  <span class="td col fs-36px text-yellow">8.3</span>
                  <span class="td col fs-36px text-red">17</span>
                  <span class="td col">
                    <div class="btn-droup hstack border border-grey rounded-15px text-dark">
                      <button type="button" class="btn shadow-none col">-</button>
                      <span class="fs-24px col">1</span>
                      <button type="button" class="btn shadow-none col">+</button>
                    </div>
                  </span>
                </div>
              </div>
            </div>
          </div>
          <div class="text-center">
            <button type="button" class="btn text-bg-brown px-5 my-4 mw-100 w-727px mw-100">Pay Now!</button>
          </div>
        </div>
        <div class="col-12 my-5 text-brown">
          <h3 class="mb-4 cart-text">
            <img src="./icon/history.svg" alt="cart-icon" width="35" height="30" />
            History
          </h3>
          <div class="table-responsive">
            <div class="table fs-28px text-center border-">
              <div class="table-head text-dark col-12 ">
                <div class="tr mycard hstack col-12 py-3 px-4 my-4 ">
                  <span class="th col">Image</span>
                  <span class="th col">Name</span>
                  <span class="th col">Categories</span>
                  <span class="th col">Price</span>
                  <span class="th col">Provider ID</span>
                  <span class="th col">Rating</span>
                  <span class="th col">In Stock</span>
                  <span class="th col">Quantity</span>
                </div>
              </div>
              <div class="table-body text-grey col-12">
                <div class="tr mycard hstack col-12 py-2 px-4 my-4">
                  <span class="td col">
                    <img src="./img/pic1.png" alt="" width="106" height="102" />
                  </span>
                  <span class="td col">Mom’s Spaghetti</span>
                  <span class="td col">Food</span>
                  <span class="td col">$21000000</span>
                  <span class="td col">1234</span>
                  <span class="td col fs-36px text-yellow">10</span>
                  <span class="td col fs-36px text-red">0</span>
                  <span class="td col fs-36px text-grey">3</span>
                </div>
                <div class="tr mycard hstack col-12 py-3 px-4 my-4">
                  <span class="td col px-2 ">
                    <img src="./img/pic2.png" alt="" width="106" height="102" />
                  </span>
                  <span class="td col px-2">Dre’s Microphone</span>
                  <span class="td col px-2 ">Technology</span>
                  <span class="td col px-2 ">$21000000</span>
                  <span class="td col px-2 ">1234</span>
                  <span class="td col px-2  fs-36px text-yellow">8.5</span>
                  <span class="td col px-2  fs-36px text-red">22</span>
                  <span class="td col px-2  fs-36px text-grey">1</span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="modal fade" id="yourCart" tabindex="-1" aria-labelledby="yourCartLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered fw-bold text">
          <div class="modal-content">
            <div class="modal-header border-bottom-0">
              <h1 class="modal-title fs-5" id="yourCartLabel">Your Cart</h1>
            </div>
            <div class="modal-body py-0">
              <ul className='my-3'>
                <li>
                  <div className="hstack">
                    <p className='me-auto text-brown'>titile * <span>34</span></p>
                    <p className='ms-auto text-light-brown'>23847239862397846</p>
                  </div>
                </li>
                <li>
                  <div className="hstack">
                    <p className='me-auto text-brown'>titile * <span>34</span></p>
                    <p className='ms-auto text-light-brown'>23847239862397846</p>
                  </div>
                </li>
              </ul>
              <div className="hstack my-3">
                <input type="search" className="form-control rounded-4 me-1" />
                <button type='submit' className='btn btn-sm btn-light-brown3 text--light py-0 px-2 rounded-3 ms-1'>Submitted</button>
              </div>
              <div className="">
                <div className="hstack">
                  <p className="ms-3 me-auto text-brown">total</p>
                  <p className="ms-auto text-light-brown">
                    <del>128346185123235e</del>
                  </p>
                </div>
                <div className="hstack">
                  <p className="ms-3 me-auto text-brown">with discount</p>
                  <p className="ms-auto text-danger fs-5">128346185123235</p>
                </div>
              </div>
            </div>
            <div class="modal-footer border-top-0 pt-0">
              <button type="button" class="btn btn-sm shadow-none text-danger" data-bs-dismiss="modal">Close</button>
              <button type="button" class="btn btn-sm btn-brown px-4">Buy!</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default User;
