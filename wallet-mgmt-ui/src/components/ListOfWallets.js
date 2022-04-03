import React,{useState,useEffect} from 'react';
import { Link } from 'react-router-dom';
import Walletservice from '../services/Walletservice';

export const ListOfWallets = () => {

    const [wallets, setWallets] = useState([])
    useEffect(() => {
      Walletservice.getWallets().then((response)=>{
          setWallets(response.data);
      }).catch(error=>{
          console.err(error);
      })
    }, [])
    
  return (
    <div className='container text-center'> 
        <h2 className='text-center'>List of Wallets</h2>
        <Link to="/add-wallet" className='btn btn-primary mb-2'>Add Wallet</Link> &nbsp;
        <Link to="/add-fund" className='btn btn-primary mb-2'>Add Fund</Link> &nbsp;
        <Link to="/withdraw-fund" className='btn btn-primary mb-2'>Withdraw Fund</Link> &nbsp;
        <Link to="/transfer-fund" className='btn btn-primary mb-2'>Transfer Fund</Link>
        <table className='table table-bordered table-striped text-center'>
            <thead>
                <th>Wallet ID</th>
                <th>Wallet Name</th>
                <th>Available Fund</th>
            </thead>
            <tbody>
                {
                    wallets.map(
                        wallet=>
                        <tr key={wallet.id}>
                            <td>{wallet.id}</td>
                            <td>{wallet.walletName}</td>
                            <td>{wallet.walletBalanceFund}</td>
                        </tr>
                    )
                }
            </tbody>
        </table>
    </div>
  )
}
