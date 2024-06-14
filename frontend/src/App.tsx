

import { BrowserRouter, Route, Routes } from 'react-router-dom'

import Login from './pages/Login'
import Register from './pages/Register'
import Forgot from './pages/Forgot'
import ForgotSent from './pages/ForgotSent'
import RegisterSent from './pages/RegisterSent'
import Home from './pages/Home'
import Goals from './pages/Goals'
import BankAccounts from './pages/BankAccounts'
import Profile from './pages/Profile'
import Transaction from './pages/Transaction'
import Savings from './pages/Calculators/Savings'
import CompoundInterest from './pages/Calculators/CompoundInterest'
import CalculatorMenu from './pages/CalculatorMenu'
import ForgotReset from './pages/ForgotReset'


function App() {
  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path='/' element={<Login/>} />
          <Route path='/register' element={<Register/>} />
          <Route path='/register-sent' element={<RegisterSent/>} />
          <Route path='/forgot-password' element={<Forgot/>} />
          <Route path='/forgot-sent' element={<ForgotSent/>} />
          <Route path='/home' element={<Home/>} />
          <Route path='/transactions' element={<Transaction/>} />
          <Route path='/goals' element={<Goals/>} />
          <Route path='/profile' element={<Profile/>} />
          <Route path='/bank-accounts' element={<BankAccounts/>} />
            <Route path='/calculators' element={<CalculatorMenu/>} />
            <Route path='/calculators/savings' element={<Savings/>} />
            <Route path='/calculators/compound-interest' element={<CompoundInterest/>} />
          <Route path='/forgot-reset' element={<ForgotReset/>} />
        </Routes>
      </BrowserRouter>
    </>
  )
}

export default App
