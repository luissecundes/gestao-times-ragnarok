import React from 'react';
import './index.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './components/pages/Home';
import ComposicaoTimes from './components/pages/ComposicaoTimes';
import Container from './components/layouts/Container';
import Navbar from './components/layouts/Navbar';
import Footer from './components/layouts/Footer';
import AddProducts from './components/controllers/AddProducts';
import AddDatas from './components/controllers/AddDatas';
import EditProducts from './components/controllers/EditProducts'; 
function App() {
  return (
    <Router>
      <Navbar />
      <Routes>
        <Route path="/" element={<Container customClass="min-height"><Home /> </Container>} />
        <Route path="/products" element={<Container customClass="min-height"> <ComposicaoTimes /></Container>} />
        <Route path="/addintegrantes" element={<Container customClass="min-height"> <AddProducts /></Container>} />
        <Route path="/adddatas" element={<Container customClass="min-height"> <AddDatas /></Container>} />
        <Route path="/producteditform/:productId" element={<Container customClass="min-height"> <EditProducts /></Container>} />
      </Routes>
      <Footer />
    </Router>
  );
}

export default App;
