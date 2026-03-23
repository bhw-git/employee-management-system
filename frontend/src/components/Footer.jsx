import React from 'react'

const Footer = () => {
  return (
    <div>
      <footer className='footer navbar-light md-3' style={{backgroundColor : '#8bcaf77a'}}>
          <br/> 
            <span>All rights reserved {new Date().getFullYear()} ©bhw</span>
      </footer>
    </div>
  )
}

export default Footer
